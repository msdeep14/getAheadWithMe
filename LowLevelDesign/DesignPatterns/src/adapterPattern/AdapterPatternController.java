package adapterPattern;

/**
 * You own a MacBook Pro and it comes with only C type ports, what if you need to plug in USB. You use USB adapter...
 *
 * Adapter pattern acts as bridge between two incompatible interfaces.
 *
 * Adapter pattern is of type Structural Design Pattern.
 *
 * [ClientClass] ---> ClientRequest, ClientResponse
 * [AdapterClass] ---> converts ClientRequest to DependencyRequest and DependencyResponse to ClientResponse
 * [DependencyClass] ---> DependencyRequest, DependencyResponse
 * */
public class AdapterPatternController {
    public static void main(String[] args) {
        new EnglishSpeakerImpl().speakEnglish("Hello English");
    }
}
