import subprocess


def ping_port(host: str) -> str:
    """Checks a port host an owner typed into the console."""
    return subprocess.run(f"ping -c 1 {host}", shell=True, capture_output=True, text=True).stdout
