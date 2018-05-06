job "config-service" {
  datacenters = ["dc1"]
  type = "service"
  group "config" {
    count = 2
    task "config-api" {
      driver = "docker"
      config {
        image = "punchat/config"
        network_mode = "punchat"
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
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