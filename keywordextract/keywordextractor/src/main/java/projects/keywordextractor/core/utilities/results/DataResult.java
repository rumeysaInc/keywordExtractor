package projects.keywordextractor.core.utilities.results;
//DataResult'ın başarılı ve başarısız olmak üzere 2 durumu var
//birden fazla veri tipi, generik <T>
public class DataResult<T> extends Result{

    private T data;
    public DataResult(T data,boolean success, String message) {
        super(success, message);
        //super base sınıfın constructor'larını çalıştırır
        this.data=data;
    }

    public DataResult(T data,boolean success) { //mesajsız
        super(success);
        this.data=data;
    }
    public T getData(){
        return this.data;
    }
}
