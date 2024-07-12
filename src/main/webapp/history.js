
    function logHistoryAndRedirect( lat, lng) {
    // Prepare the URL and parameters
    const url = 'loghistory.jsp';
    const params = new URLSearchParams();
    params.append('lat', lat);
    params.append('lng', lng);

    // Use fetch to send the data
    fetch(url, {
    method: 'POST', // Using POST method
    body: params.toString(), // Sending the parameters as form data
    headers: {
    'Content-Type': 'application/x-www-form-urlencoded' // Necessary header for POST request with form data
}
})
    .then(response => response.text()) // Processing the response to text
    .then(result => {
    console.log('Success:', result);
})
    .catch(error => {
    console.error('Error during fetch:', error);
    alert('Failed to log history.');
});
}

