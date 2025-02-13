function getLocation() {
    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(function (position) {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            document.getElementById('LAT').value = latitude;
            document.getElementById('LNT').value = longitude;

        }, function (error) {
            console.error('Error obtaining location:', error);
            document.getElementById('location').textContent = 'Failed to retrieve location: ' + error.message;
        });
    } else {
        document.getElementById('location').textContent = 'Geolocation is not supported by this browser.';
    }
}

function sendLocation() {
    var LAT = document.getElementById('LAT').value;
    var LNT = document.getElementById('LNT').value;
    if (LNT <= 0 || LAT <= 0) {
        return console.log("wrong coordinate")
    }

    var newUrl = "?LAT="+encodeURIComponent(LAT)+"&LNT="+encodeURIComponent(LNT);
    window.history.pushState({path: newUrl}, '', newUrl);


    fetch(newUrl, {
        method: 'get'
    }).then(response => response.text()) // Convert the response into text
        .then(html => {
            document.getElementById('html').innerHTML = html; // Updates the HTML content
        })
    .catch(error => console.error('Error', error));
}