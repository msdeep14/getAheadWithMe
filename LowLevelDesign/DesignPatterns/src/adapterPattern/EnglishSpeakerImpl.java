package adapterPattern;

public class EnglishSpeakerImpl {
    public void speakEnglish(String english) {
        System.out.println("Speaking English: " + english);
        EnglishToGermanAdapter englishToGermanAdapter = new EnglishToGermanAdapter();
        englishToGermanAdapter.speak(english);
    }
}
