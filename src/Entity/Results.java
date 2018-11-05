package Entity;

import java.util.Map;

/**
 * @author LongYi
 * @date 2018/10/28 21:01
 * @log: 1.
 */
public class Results {
    private Boolean success;

    private String file_path;
    private Map<String, Object> result;

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
