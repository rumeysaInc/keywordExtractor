package projects.keywordextractor.core.utilities.results;

public class SuccessResult extends Result{

    public SuccessResult(){
        super(true);
    }

    public SuccessResult(String message){ //mesaj verilecekse
        super(true,message);
    }

}
