from flask import Flask, render_template
from flask_cors import CORS
import mysql.connector

app = Flask(__name__)
CORS(app)

# Creating a db connection
connection = mysql.connector.connect(
    host="127.0.0.1",
    user="root",
    port="3307",
    password="lodesmain@21",
    database="mlopss"
)

# Checking the connection
if connection.is_connected():
    print("Connected to MySQL as id", connection.connection_id)
else:
    print("Error connecting to MySQL")

@app.route("/")
def index():
    try:
        cursor = connection.cursor()
        cursor.execute("SELECT * FROM user1")
        results = cursor.fetchall()
        cursor.close()
        return render_template("index.html", data=results)
    except mysql.connector.Error as err:
        print("Error querying the database:", err)
        return "Internal Server Error", 500

if __name__ == "__main__":
    app.run(port=4000, debug=True)
