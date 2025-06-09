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

  req.on('end', () => {
    res.status(200).json({ message: '파일 저장 성공', filename });
  });

  req.on('error', (err) => {
    console.error('업로드 실패:', err);
    res.status(500).json({ error: '파일 저장 실패', detail: err.message });
  });
});

app.listen(PORT, () => {
  console.log(`📦 upload-server running on port ${PORT}`);
});
