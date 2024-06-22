package projects.keywordextractor.core.utilities.results;

public class ErrorDataResult<T> extends DataResult{

    public ErrorDataResult(T data, String message){
        super(data,false,message);
    }
    public ErrorDataResult(T data){//mesajsız
        super(data,false);
    }
    public ErrorDataResult(String message){ //datasız
        super(null,false,message);
    }

    public ErrorDataResult(){ //data,success
        super(null,false);
    }
}
