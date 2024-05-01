'use strict';

class Carousel {
  constructor(el) {
    this.el = el;
    this.carouselOptions = ['previous', 'play', 'next']; // add 옵션 제거
    this.carouselData = [
        {
          'id': '1',
          'src': '/sangsangjakka/resources/img/book1.jpg', // 변경된 이미지 주소
        },
        {
          'id': '2',
          'src': '/sangsangjakka/resources/img/book2.jpg', // 변경된 이미지 주소
        },
        {
          'id': '3',
          'src': '/sangsangjakka/resources/img/book3.jpg', // 변경된 이미지 주소
        },
        {
          'id': '4',
          'src': '/sangsangjakka/resources/img/book4.jpg', // 변경된 이미지 주소
        },
        {
          'id': '5',
          'src': '/sangsangjakka/resources/img/book5.jpg', // 변경된 이미지 주소
        }
      ];
      
    this.carouselInView = [1, 2, 3, 4, 5];
    this.carouselContainer;
    this.carouselPlayState;
  }

  changeBookDescription(bookIndex) {
    const bookDescriptions = [
      {
          title: "제목: 세상에서 두 번째로 신기한 일",
          intro: "한 줄 소개: 첫 번째로 신기한 일은 과연 무엇이었을까요? 맞춰보세요!",
          genre: "태그: #미스터리 #신비한 #여행"
      },
      {
        title: "제목: 할아버지와 순돌이는 닮았어요.",
        intro: "한 줄 소개: 세상에서 가장 따뜻한 강아지와 할아버지의 함께 살아가는 이야기입니다.",
        genre: "태그: #감동적인 #따뜻한 #감사의"
      },
      {
        title: "제목: 화가 나서 그랬어!",
        intro: "한 줄 소개: 화가 나는데 어떡해요..감정을 표현하는 방법을 알려드립니다.",
        genre: "태그: #귀여운 #장난스러운 #재미있는"
      },
      {
        title: "제목: 당근 유치원",
        intro: "한 줄 소개: 토끼 선생님과 함께하는 당근 유치원이야기 입니다!",
        genre: "태그: #감동적인 #귀여운 #감사의"
      },
      {
        title: "제목: To the Sea",
        intro: "한 줄 소개: 고래를 타고 바다로 나아가는 태리의 여정입니다.",
        genre: "태그: #동물적인 #따뜻한 #사랑스러운"
      }
      
        
        
        // 여기에 책에 대한 추가 정보를 넣으세요.
    ];

    // 선택한 책에 맞는 설명을 업데이트합니다.
    const bookTitle = document.querySelector('.recommendbookTitle');
    const bookIntro = document.querySelector('.recommendbookIntro');
    const bookGenre = document.querySelector('.recommendbookGenre');

    bookTitle.textContent = bookDescriptions[bookIndex].title;
    bookIntro.textContent = bookDescriptions[bookIndex].intro;
    bookGenre.textContent = bookDescriptions[bookIndex].genre;
}

  mounted() {
    this.setupCarousel();
  }

  // Build carousel html
  setupCarousel() {
    const container = document.createElement('div');
    const controls = document.createElement('div');

    // Add container for carousel items and controls
    this.el.append(container, controls);
    container.className = 'carousel-container';
    controls.className = 'carousel-controls';

    // Take dataset array and append items to container
    this.carouselData.forEach((item, index) => {
      const carouselItem = item.src ? document.createElement('img') : document.createElement('div');

      container.append(carouselItem);
      
      // Add item attributes
      carouselItem.className = `carousel-item carousel-item-${index + 1}`;
      carouselItem.src = item.src;
      carouselItem.setAttribute('loading', 'lazy');
      // Used to keep track of carousel items, infinite items possible in carousel however min 5 items required
      carouselItem.setAttribute('data-index', `${index + 1}`);
    });

    this.carouselOptions.forEach((option) => {
      const btn = document.createElement('button');
      const axSpan = document.createElement('span');


      // Add button attributes
      btn.className = `carousel-control carousel-control-${option}`;
      btn.setAttribute('data-name', option);

      // Add carousel control options
      controls.append(btn);
    });

    // After rendering carousel to our DOM, setup carousel controls' event listeners
    this.setControls([...controls.children]);

    // Set container property
    this.carouselContainer = container;
  }

  setControls(controls) {
    controls.forEach(control => {
      control.onclick = (event) => {
        event.preventDefault();

        // Manage control actions, update our carousel data first then with a callback update our DOM
        this.controlManager(control.dataset.name);
      };
    });
  }

  controlManager(control) {
    if (control === 'previous') return this.previous();
    if (control === 'next') return this.next();
    if (control === 'play') return this.play();

    return;
  }

  previous() {
    // Update order of items in data array to be shown in carousel
    this.carouselData.unshift(this.carouselData.pop());

    // Push the first item to the end of the array so that the previous item is front and center
    this.carouselInView.push(this.carouselInView.shift());

    // Update the css class for each carousel item in view
    this.carouselInView.forEach((item, index) => {
      this.carouselContainer.children[index].className = `carousel-item carousel-item-${item}`;
    });

    // Using the first 5 items in data array update content of carousel items in view
    this.carouselData.slice(0, 5).forEach((data, index) => {
      document.querySelector(`.carousel-item-${index + 1}`).src = data.src;
    });

    // Change book description
    this.changeBookDescription(this.carouselInView[2] - 1); // 중앙에 있는 책의 인덱스는 항상 2입니다.
  }

  next() {
    // Update order of items in data array to be shown in carousel
    this.carouselData.push(this.carouselData.shift());

    // Take the last item and add it to the beginning of the array so that the next item is front and center
    this.carouselInView.unshift(this.carouselInView.pop());

    // Update the css class for each carousel item in view
    this.carouselInView.forEach((item, index) => {
      this.carouselContainer.children[index].className = `carousel-item carousel-item-${item}`;
    });

    // Using the first 5 items in data array update content of carousel items in view
    this.carouselData.slice(0, 5).forEach((data, index) => {
      document.querySelector(`.carousel-item-${index + 1}`).src = data.src;
    });

    // Change book description
    this.changeBookDescription(this.carouselInView[2] - 1); // 중앙에 있는 책의 인덱스는 항상 2입니다.
  }

  play() {
    const playBtn = document.querySelector('.carousel-control-play');
    const startPlaying = () => this.next();

    if (playBtn.classList.contains('playing')) {
      // Remove class to return to play button state/appearance
      playBtn.classList.remove('playing');

      // Remove setInterval
      clearInterval(this.carouselPlayState); 
      this.carouselPlayState = null; 
    } else {
      // Add class to change to pause button state/appearance
      playBtn.classList.add('playing');

      // First run initial next method
      this.next();

      // Use play state prop to store interval ID and run next method on a 1.5 second interval
      this.carouselPlayState = setInterval(startPlaying, 1500);
    };
  }

}

// Refers to the carousel root element you want to target, use specific class selectors if using multiple carousels
const el = document.querySelector('.carousel');
// Create a new carousel object
const exampleCarousel = new Carousel(el);
// Setup carousel and methods
exampleCarousel.mounted();
