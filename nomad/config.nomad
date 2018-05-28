job "config-service" {
  datacenters = ["dc1"]
  type = "service"
  group "config" {
    count = 1
    task "config-api" {
      driver = "docker"
      config {
        image = "punchat/config"
        network_mode = "punchat"
        network_aliases = ["config"]
        volumes = [
          "/var/log/punchat/${appName}/:/logs/${appName}/"
        ]
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        appName = "config"
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