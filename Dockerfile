# Use the official Ubuntu base image
FROM ubuntu:latest

# Update package lists and install Apache2
RUN apt-get update && \
    apt-get install -y apache2 && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
    echo 'ServerName localhost' >> /etc/apache2/apache2.conf && \
    chown -R www-data:www-data /var/www/html

# Copy index.html to the Apache document root
COPY index.html /var/www/html/

# Expose port 80 to access the web server
EXPOSE 80

# Start Apache in the foreground
CMD ["apachectl", "-D", "FOREGROUND"]
