document.getElementById('add-post-form').addEventListener('submit', function(event) {
    event.preventDefault();


    const formData = new FormData(event.target);
    const title = formData.get('post-title');
    const content = formData.get('post-content');
    const image = formData.get('post-image');


    if (!title.trim() || !content.trim() || !image) {
        alert('Please fill in all fields and select an image.');
        return;
    }


    const xhr = new XMLHttpRequest();


    xhr.open('POST', '/PostServlet');
    xhr.setRequestHeader('Accept', 'application/json');


    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {

            const responseData = JSON.parse(xhr.responseText);
            console.log('Post uploaded successfully:', responseData);

        } else {

            console.error('Error uploading post:', xhr.statusText);
            alert('Failed to upload post. Please try again later.');
        }
    };


    xhr.onerror = function() {
        console.error('Error uploading post:', xhr.statusText);
        alert('Failed to upload post due to network error. Please try again later.');
    };


    xhr.send(formData);
});
