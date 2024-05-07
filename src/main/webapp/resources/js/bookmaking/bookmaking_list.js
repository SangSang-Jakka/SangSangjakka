/* bookmaking_list.js */
document.addEventListener('DOMContentLoaded', function() {
    let currentSlide = 0;
    const sliderItems = document.querySelectorAll('.sliderItem');
    const sliderItemBox = document.querySelector('.sliderItemBox');
    const numItems = sliderItems.length;
    const visibleItems = 4;
    const maxSlide = numItems - visibleItems;

    function updateButtons() {
        document.querySelector('.sliderBtn.left').style.display = numItems > visibleItems ? 'block' : 'none';
        document.querySelector('.sliderBtn.right').style.display = numItems > visibleItems ? 'block' : 'none';
    }

    function moveSlide(direction) {
        currentSlide += direction;
        if (currentSlide > maxSlide) {
            currentSlide = 0;
        } else if (currentSlide < 0) {
            currentSlide = maxSlide;
        }
        const movePercentage = -100 * (currentSlide / visibleItems);
        sliderItemBox.style.transform = `translateX(${movePercentage}%)`;
    }

    updateButtons();
    
    document.querySelector('.sliderBtn.left').onclick = () => moveSlide(-1);
    document.querySelector('.sliderBtn.right').onclick = () => moveSlide(1);
});
