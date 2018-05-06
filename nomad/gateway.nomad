job "gateway-service" {
  datacenters = ["dc1"]
  type = "service"
  group "gateway" {
    count = 1
    task "gateway-api" {
      driver = "docker"
      config {
        image = "punchat/gateway"
        network_mode = "punchat"
      }
      env {
        port = 8080
      }
      resources {
        cpu = 500
        memory = 500
        network {
          port = 8080
        }
      }
    }
    restart {
      attempts = 1
    }
  }
}