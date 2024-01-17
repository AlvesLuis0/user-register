(function() {
  const form = document.querySelector('.needs-validation');
  form.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(form);
    const user = Object.fromEntries(formData.entries());
    const path = '/user/register';
    const request = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(user)
    };
    fetch(path, request);
    alert('Check your email to verify your account');
    location.assign('/login.html');
  });
})();