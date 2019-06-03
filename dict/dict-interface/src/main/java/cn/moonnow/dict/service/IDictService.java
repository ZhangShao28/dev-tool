package cn.moonnow.dict.service;

import java.util.Collection;

import cn.moonnow.dict.entity.Dict;

public interface IDictService {

  public void saveDict(Dict dict) throws Exception;
  
  public void batchSaveDict(Collection<Dict> dicts) throws Exception;
  
  public void updateDict(Dict dict) throws Exception;
    
  public void batchUpdateDict(Collection<Dict> dicts) throws Exception;
  
  public void removeDict(Dict dict) throws Exception;
  
  public void batchRemoveDict(Collection<Dict> dicts) throws Exception;

}
