package projects.keywordextractor.business.concretes;

import org.springframework.stereotype.Service;
import projects.keywordextractor.RestClient.RestRequest;
import projects.keywordextractor.business.abstracts.ExtractKeywordService;
import projects.keywordextractor.request.concretes.ArticleRequest;
import projects.keywordextractor.request.concretes.KeywordRequest;

import java.util.List;
@Service
public class ExtractKeywordManager implements ExtractKeywordService {
    private final RestRequest restRequest;

    public ExtractKeywordManager(RestRequest restRequest) {
        this.restRequest = restRequest;
    }
    @Override
    public List<String> extractKeywords(ArticleRequest articleRequest) {
        //List<String> keywords = extractKeywordsFromText(articleRequest.getText());
        KeywordRequest keywordRequest= new KeywordRequest(articleRequest.getText());
        if (articleRequest.getCategory().equals("haber")){
            return restRequest.newsKeyword(keywordRequest);
        }
        return restRequest.scholarKeyword(keywordRequest);
    }

    /*private List<String> extractKeywordsFromText(String text) {
        // Anahtar kelimelerin çıkarılması için gereken işlem burada gerçekleştirilebilir
        // Bu örnekte, metni kelimelere ayırarak basitçe anahtar kelimeleri alıyorum
        String[] words = text.split("\\s+");
        return List.of(words);
    }*/
}
