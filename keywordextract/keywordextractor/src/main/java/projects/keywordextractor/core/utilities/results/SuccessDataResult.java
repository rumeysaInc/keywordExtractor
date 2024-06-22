package projects.keywordextractor.core.utilities.results;

public class SuccessDataResult<T> extends  DataResult {

    public SuccessDataResult(T data, String message){
        super(data,true,message);
    }
    public SuccessDataResult(T data){//mesajsız
        super(data,true);
    }
    public SuccessDataResult(String message){ //datasız
        super(null,true,message);
    }

    public SuccessDataResult(){ //data,success
        super(null,true);
    }
}
