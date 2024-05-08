/* bookmaking_view.js */
$(document).ready(function() {
	$('.bookmakingWrap').hide();
	$('.coverMaker').hide();
	$('.titleMaker').hide();
	
	function initBookmaking() {
		$('.bookmakingOptionContainer').hide();
		$('.bookmakingWrap').show();
		Page.init();

		var bookType = this.id;
		$('#textAiSupport').prop('checked', bookType === 'recommendedBook');
		$('#imageAiSupport').prop('checked', bookType === 'recommendedBook');

		$('#textAiSupport').trigger('change');
		$('#imageAiSupport').trigger('change');
	}

	$('#recommendedBook, #customBook').click(initBookmaking);

	$('#textAiSupport').change(function() {
		if ($(this).is(':checked')) {
			$('.pageTextMakerBox').show();
			$('.pageDescriptionBox').hide();
		} else {
			$('.pageTextMakerBox').hide();
			$('.pageDescriptionBox').show();
		}
	});

	$('#imageAiSupport').change(function() {
		if ($(this).is(':checked')) {
			$('.pageImageDesBox').show();
			$('.pageImageMakerBox').show();
			$('.pageImageUploadBox').hide();
		} else {
			$('.pageImageDesBox').hide();
			$('.pageImageMakerBox').hide();
			$('.pageImageUploadBox').show();
		}
	});

	$('#bb-bookblock .bb-item').hide().first().show();

	$('#pageAdd').click(function() {
		var $newPage = $('<div class="bb-item"></div>').html(`
			<div class="pageImage" style="background-image: url('/sangsangjakka/resources/img/empty.jpg');"></div>
			<p>내용</p>
		`);
		$('#bb-bookblock').append($newPage);
		Page.init();  // Reinitialize or setup for the new page if necessary
		$('#bb-nav-last').click();	// Navigate to the last page
		$('.pageMaker').show();
	});

	$('#bb-nav-next').click(function() {
		var $visiblePage = $('#bb-bookblock .bb-item:visible');
		var $nextPage = $visiblePage.next('.bb-item');
		if ($nextPage.length > 0) {
			$visiblePage.hide();
			$nextPage.show();
		}
	});

	$('#bb-nav-prev').click(function() {
		var $visiblePage = $('#bb-bookblock .bb-item:visible');
		var $prevPage = $visiblePage.prev('.bb-item');
		if ($prevPage.length > 0) {
			$visiblePage.hide();
			$prevPage.show();
		}
	});

	$('#pageDelete').click(function() {
		var $currentVisible = $('#bb-bookblock .bb-item:visible');
		if ($currentVisible.prev('.bb-item').length) {
			$currentVisible.remove();
			Page.init();  // Reinitialize after deleting a page
			$('#bb-nav-last').click();	// Navigate to the last page
		} else {
			alert("Cannot delete the first page.");  // Added alert to prevent deletion of the first page
		}
	});
	
	// Click handler for the "전송" button
	$('.pageDescriptionBox input[type="button"]').click(function() {
		// Fetch the text from the input field
		var newText = $(this).closest('.pageDescriptionBox').find('input[type="text"]').val();

		// Find the visible bb-item and update its <p> tag with the new text
		var $visibleBbItem = $('#bb-bookblock .bb-item:visible');
		$visibleBbItem.find('p').text(newText);
	});
	
	$('.pageImageUploadBox input[type="button"]').click(function() {
		var fileInput = document.getElementById('pageImageUpload');
		if (fileInput.files && fileInput.files[0]) {
			var reader = new FileReader();
			
			reader.onload = function(e) {
				// Get the data URL of the image file
				var imageUrl = e.target.result;

				// Find the visible bb-item and update its .pageImage div with the new background image
				var $visiblePageImage = $('#bb-bookblock .bb-item:visible .pageImage');
				$visiblePageImage.css('background-image', 'url(' + imageUrl + ')');

				// Reset the file input after updating the image
				fileInput.value = ''; // This line resets the file input
			};

			// Read the image file as a data URL to use as a background image
			reader.readAsDataURL(fileInput.files[0]);
		} else {
			alert('Please select an image file to upload.');
		}
	});
	
	
	$('#pageNext').click(function() {
		$('.makedPageViewer').hide();
		$('.pageMaker').hide();
		$('.coverMaker').show();
	});

	$('#coverNext').click(function() {
		$('.coverMaker').hide();
		$('.titleMaker').show();
	});
	
	$('#titlePrev').click(function() {
		$('.titleMaker').hide();
		$('.coverMaker').show();
	});
	
	$('#coverPrev').click(function() {
		$('.coverMaker').hide();
		$('.makedPageViewer').show();
	});
	
	$('#pageEdit').click(function() {
		$('.pageMaker').show();
	});

	var Page = (function() {

		var config = {
			$bookBlock: $('#bb-bookblock'),
			$navNext: $('#bb-nav-next'),
			$navPrev: $('#bb-nav-prev'),
			$navFirst: $('#bb-nav-first'),
			$navLast: $('#bb-nav-last')
		},
			init = function() {
				config.$bookBlock.bookblock({
					speed: 800,
					shadowSides: 0.8,
					shadowFlip: 0.7
				});
				initEvents();
			},
			initEvents = function() {

				var $slides = config.$bookBlock.children();

				// add navigation events
				config.$navNext.on('click touchstart', function() {
					config.$bookBlock.bookblock('next');
					$('.pageMaker').hide();
					return false;
				});

				config.$navPrev.on('click touchstart', function() {
					config.$bookBlock.bookblock('prev');
					$('.pageMaker').hide();
					return false;
				});

				config.$navFirst.on('click touchstart', function() {
					config.$bookBlock.bookblock('first');
					$('.pageMaker').hide();
					return false;
				});

				config.$navLast.on('click touchstart', function() {
					config.$bookBlock.bookblock('last');
					$('.pageMaker').hide();
					return false;
				});

				// add swipe events
				$slides.on({
					'swipeleft': function(event) {
						config.$bookBlock.bookblock('next');
						return false;
					},
					'swiperight': function(event) {
						config.$bookBlock.bookblock('prev');
						return false;
					}
				});

				// add keyboard events
				$(document).keydown(function(e) {
					var keyCode = e.keyCode || e.which,
						arrow = {
							left: 37,
							up: 38,
							right: 39,
							down: 40
						};

					switch (keyCode) {
						case arrow.left:
							config.$bookBlock.bookblock('prev');
							break;
						case arrow.right:
							config.$bookBlock.bookblock('next');
							break;
					}
				});
			};

		return { init: init };

	})();

	Page.init();
});