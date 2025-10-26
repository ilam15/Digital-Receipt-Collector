// Minimal interactivity for App landing page
document.addEventListener('DOMContentLoaded', function(){
	// graceful fallback if image missing
	const img = document.querySelector('.device-mockup img');
	if(img){
		img.onerror = () => { img.style.display = 'none'; };
	}
});
