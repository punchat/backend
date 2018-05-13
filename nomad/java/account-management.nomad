job "am-service" {
  datacenters = ["dc1"]
  type = "service"
  group "account-management" {
    count = 2
    task "am-api" {
      driver = "java"
      config {
        jar_path = "local/account-management.jar"
        jvm_options = ["-Xmx400m", "-Xms256m"]
      }
      artifact {
        source      = "http://167.99.139.178:8082/services/config/target/account-management.jar"
      }
      env {
        PORT = "${NOMAD_HOST_PORT_http}"
        clientId = "am"
        clientSecret = "pass"
        accessTokenUri = "http://167.99.139.178:8080/uaa/oauth/token"
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
