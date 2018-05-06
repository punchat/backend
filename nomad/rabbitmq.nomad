job "rabbitmq-service" {
  datacenters = ["dc1"]
  type = "service"
  group "rabbitmq" {
    count = 1
    task "rabbitmq" {
      driver = "docker"
      config {
        image = "rabbitmq:management"
        network_mode = "punchat"
        hostname = "rabbitmq"
        network_aliases = ["rabbitmq"]
        port_map {
          amqp = 5672
          http = 15672
        }
      }
      resources {
        cpu = 300
        memory = 500
        network {
          port "amqp" {}
          port "http" {}
        }
      }
    }
    restart {
      attempts = 1
    }
  }
}