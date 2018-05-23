import spock.lang.Specification

class InvitationServiceSpec extends Specification {
    def publisher = new Publisher()
    Subscriber sub1 = Mock(Subscriber)
    Subscriber sub2 = Mock(Subscriber)

    void setup() {
        publisher.subscribers << sub1 << sub2
    }

    def "deliver event"() {
        when:
        publisher.send("event")

        then:
        1 * sub1.receive("event")
        1 * sub2.receive("event")
    }

    def "test"() {
        def test = GroovyMock(Subscriber) {
            receive(_) >> ['ok', 'lo']
        }

        given:
        sub1.receive(_) >> { throw new Exception() }

        when:
        publisher.send("event1")
        publisher.send("event2")

        then:
        1 * sub2.receive("event1")
        1 * sub2.receive("event2")
    }
}
class Publisher {
    def subscribers = []

    def send(event) {
        subscribers.each {
            try {
                it.receive(event)
            } catch (Exception e) {

            }
        }
    }
}

interface Subscriber {
    void receive(event)
}
