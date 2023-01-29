package adapterPattern;

public class EnglishToGermanAdapter {
    public EnglishToGermanAdapter() {

    }
    public void speak(String english) {
        System.out.println("Translating English to German");
        String german = "Hello german!!!";
        new GermanSpeakerImpl().speakGerman(german);
    }
}
