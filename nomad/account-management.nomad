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
        volumes = [
          "/var/log/punchat/${appName}/:/logs/${appName}/"
        ]
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "am"
        clientSecret = "pass"
        ADMIN_EMAIL = "A.S.Ivchenko@yandex.ru"
        appName = "account-management"
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
