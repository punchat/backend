job "uaa-service" {
  datacenters = ["dc1"]
  type = "service"
  group "uaa" {
    count = 1
    task "uaa-api" {
      driver = "docker"
      config {
        image = "punchat/uaa"
        network_mode = "punchat"
        volumes = [
          "/var/log/punchat/${appName}/:/logs/${appName}/"
        ]
      }
      env {
        port = "${NOMAD_HOST_PORT_http}"
        clientId = "uaa"
        clientSecret = "secret"
        appName = "auth"
        DATABASE = "uaa"
      }
      resources {
        cpu = 300
        memory = 500
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