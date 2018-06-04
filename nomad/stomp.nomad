job "stomp-notifications-delivery-service" {
  datacenters = ["dc1"]
  type = "service"
  group "stomp-notifications-delivery-service" {
    count = 1
    task "stomp-notifications-delivery-service" {
      driver = "docker"
      config {
        image = "punchat/stomp-notifications-delivery"
        network_mode = "punchat"
        volumes = [
          "/var/log/punchat/${appName}/:/logs/${appName}/"
        ]
      }
      env {
        port = "${NOMAD_HOST_PORT_http}"
        clientId = "stomp"
        clientSecret = "secret"
        appName = "stomp"
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