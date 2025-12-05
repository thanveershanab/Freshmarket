Write-Host "Checking for existing processes on port 8080..."
$port = 8080
$tcpParams = @{
    Localagent  = "TCP"
    ErrorAction = "SilentlyContinue"
}
$process = Get-NetTCPConnection -LocalPort $port -ErrorAction SilentlyContinue | Select-Object -ExpandProperty OwningProcess -Unique

if ($process) {
    Write-Host "Stopping process $process using port $port..."
    Stop-Process -Id $process -Force -ErrorAction SilentlyContinue
    Start-Sleep -Seconds 2
}

Write-Host "Starting FreshMarket..."
& "C:\Users\ADMIN\.gemini\maven\apache-maven-3.9.6\bin\mvn.cmd" spring-boot:run
Read-Host -Prompt "Press Enter to exit"
