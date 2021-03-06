job "notifications-service" {
  datacenters = ["dc1"]
  type = "service"
  group "notifications" {
    count = 1
    task "notifications" {
      driver = "docker"
      config {
        image = "punchat/notifications"
        network_mode = "punchat"
        volumes = [
          "/var/log/punchat/${appName}/:/logs/${appName}/"
        ]
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        appName = "notifications"
      }
      resources {
        cpu = 300
        memory = 450
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