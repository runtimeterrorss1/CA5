# Use an official Python runtime as the base image
FROM python:3

# Set the working directory in the container
WORKDIR /app

# Copy and install application dependencies
COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of your application code
COPY . /app

# Expose the port your Flask app will run on (default is 5000)
EXPOSE 4000

# Define the command to start your Flask application
CMD [ "python", "app.py" ]
