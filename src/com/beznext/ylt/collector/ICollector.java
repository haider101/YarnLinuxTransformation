package com.beznext.ylt.collector;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.beznext.ylt.key.MixKey;

public interface ICollector {
	
	public Map<MixKey,List<?>> collect();
	
	public List<String> getFilesToArchive();

}
