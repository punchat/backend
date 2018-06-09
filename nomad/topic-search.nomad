job "topic-search-service" {
  datacenters = ["dc1"]
  type = "service"
  group "topics" {
    count = 1
    task "topics-api" {
      driver = "docker"
      config {
        image = "punchat/topic-search"
        network_mode = "punchat"
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "topics"
        clientSecret = "pass"
      }
      resources {
        cpu = 300
        memory = 400
        network {
          port "http" {}
        }
      }
    }
    restart {
      attempts = 1
    }
  }
}