job "stomp-notification-delivery-service" {
  datacenters = ["dc1"]
  type = "service"
  group "stomp-notification-delivery-service" {
    count = 1
    task "stomp-notification-delivery-service" {
      driver = "docker"
      config {
        image = "punchat/stomp-notification-delivery"
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