job "am-service" {
  datacenters = ["dc1"]
  type = "service"
  group "account-management" {
    count = 1
    task "am-api" {
      driver = "docker"
      config {
        image = "punchat/account-management"
        network_mode = "punchat"
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "am"
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