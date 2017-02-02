/**
 * Created by user on 02.02.2017.
 */
public class PhraseFilter implements Filter {
    private String typeOfRequest;
    private String phrase;

    public PhraseFilter(String typeOfRequest, String phrase) {
        this.typeOfRequest = typeOfRequest;
        this.phrase = phrase;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if(typeOfRequest.equals("start"))
            return qe.getInfo().startsWith(this.phrase);
        else if (typeOfRequest.endsWith("end"))
            return qe.getInfo().endsWith(this.phrase);
        else if (typeOfRequest.equals("any"))
            return qe.getInfo().indexOf(this.phrase)>=0;
        else {
            return false;
        }
    }

    @Override
    public String getName() {
        return "Phrase ";
    }
}
