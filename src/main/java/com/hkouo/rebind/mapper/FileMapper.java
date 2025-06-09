package com.hkouo.rebind.mapper;

import com.hkouo.rebind.model.FileMetadata;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    void insertFile(FileMetadata file);

}
