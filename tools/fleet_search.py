import sqlite3


def find_ships(db_path: str, owner: str):
    """Lists the ships an owner typed into the console search box."""
    conn = sqlite3.connect(db_path)
    return conn.execute(f"SELECT name, port FROM ships WHERE owner = '{owner}'").fetchall()
