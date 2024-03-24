document.addEventListener('DOMContentLoaded', function() {

    function fetchComments() {

        fetch('/get-comments')
            .then(response => response.json())
            .then(comments => {

                document.getElementById('comments-list').innerHTML = '';


                comments.forEach(comment => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                        <p>${comment.content}</p>
                        <span class="timestamp">Posted on: ${comment.timestamp}</span>
                    `;
                    document.getElementById('comments-list').appendChild(li);
                });
            })
            .catch(error => console.error('Error fetching comments:', error));
    }


    fetchComments();


    document.getElementById('add-comment-form').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission


        const commentContent = document.getElementById('comment').value;


        if (commentContent.trim() === '') {
            alert('Please enter a comment.');
            return;
        }


        const formData = new FormData();
        formData.append('content', commentContent);


        fetch('/add-comment', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to add comment.');
            }
            return response.json();
        })
        .then(data => {

            document.getElementById('comment').value = '';


            fetchComments();
        })
        .catch(error => console.error('Error adding comment:', error));
    });
});
