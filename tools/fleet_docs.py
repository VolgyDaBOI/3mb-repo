def read_ship_document(name: str) -> bytes:
    """Returns a document an owner uploaded for a ship."""
    with open(f"/srv/ships/documents/{name}", "rb") as handle:
        return handle.read()
