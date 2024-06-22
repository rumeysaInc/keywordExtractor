package projects.keywordextractor.core.utilities.results;
//işlemlerimizde işlem başarılı mı başarısız mı, data gönderilecek mi bilgisi için resultlar oluşturduk
//bu resultları tüm requestlerde kullanıcaz
//Result'ın başarılı ve başarısız olmak üzere 2 durumu var
public class Result {
    private boolean success;
    private String message;

    public Result(boolean success){ //sadece success bilgisi
        this.success=success;

    }

    public Result(boolean success,String message){ // success ve message  bilgisi
        this(success); //tek parametreli constructor'ı çağırır
        this.message=message;
    }


    //dışardan erişim sağlamak için
    public boolean isSuccess(){
        return this.success;
    }

    public String getMessage(){
        return this.message;
    }

}
