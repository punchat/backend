job "email-sender-service" {
  datacenters = ["dc1"]
  type = "service"
  group "emails" {
    count = 1
    task "emails-api" {
      driver = "docker"
      config {
        image = "punchat/email-sender"
        network_mode = "punchat"
        volumes = [
          "/var/log/punchat/${appName}/:/logs/${appName}/"
        ]
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "emails"
        clientSecret = "pass"
        appName = "emails"
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