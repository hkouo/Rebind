const express = require('express');
const fs = require('fs');
const path = require('path');

const app = express();
const PORT = 8080;

app.put('/upload', (req, res) => {
  const subdir = req.query.subdir || '';
  const filename = req.query.filename;
  if (!filename) {
    return res.status(400).json({ error: 'filename 파라미터 누락' });
  }

  const targetDir = path.join('/uploads', subdir);
  const targetPath = path.join(targetDir, filename);

  try {
    fs.mkdirSync(targetDir, { recursive: true });
  } catch (e) {
    return res.status(500).json({ error: '디렉토리 생성 실패', detail: e.message });
  }

  const writeStream = fs.createWriteStream(targetPath);
  req.pipe(writeStream);

  // 파일 스트림이 완전히 종료된 후 응답을 반환
  writeStream.on('finish', () => {
    res.status(200).json({ message: '파일 저장 성공', filename });
  });

  // 업로드 과정에서 발생한 오류 처리
  writeStream.on('error', (err) => {
    console.error('파일 저장 실패:', err);
    res.status(500).json({ error: '파일 저장 실패', detail: err.message });
  });

  req.on('error', (err) => {
    console.error('업로드 실패:', err);
    res.status(500).json({ error: '파일 저장 실패', detail: err.message });
  });
});

app.listen(PORT, () => {
  console.log(`📦 upload-server running on port ${PORT}`);
});
