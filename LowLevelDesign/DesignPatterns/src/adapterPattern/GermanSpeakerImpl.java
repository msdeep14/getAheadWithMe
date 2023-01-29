package adapterPattern;

public class GermanSpeakerImpl implements GermanSpeaker {
    @Override
    public void speakGerman(String german) {
        System.out.println("Speaking German: " + german);
    }
}
