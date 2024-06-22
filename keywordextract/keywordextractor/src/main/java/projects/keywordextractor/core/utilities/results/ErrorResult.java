package projects.keywordextractor.core.utilities.results;

public class ErrorResult extends Result{

    public ErrorResult(){
        super(false);
    }

    public ErrorResult(String message){ //mesaj verilecekse
        super(false,message);
    }
}
