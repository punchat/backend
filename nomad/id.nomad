job "id-service" {
  datacenters = ["dc1"]
  type = "service"
  group "id" {
    count = 2
    task "id-api" {
      driver = "docker"
      config {
        image = "punchat/id"
        network_mode = "punchat"
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "id"
        clientSecret = "pass"
      }
      resources {
        cpu = 300
        memory = 300
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