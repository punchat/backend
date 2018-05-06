job "uaa-service" {
  datacenters = ["dc1"]
  type = "service"
  group "uaa" {
    count = 2
    task "uaa-api" {
      driver = "docker"
      config {
        image = "punchat/uaa"
        network_mode = "punchat"
      }
      env {
        port = "${NOMAD_HOST_PORT_http}"
        clientId = "uaa"
        clientSecret = "secret"
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