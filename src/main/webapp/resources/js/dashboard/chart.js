// charts.js

// 성별 차트
function drawGenderChart(chartData) {
    var context = document.getElementById('genderChart').getContext('2d');
    var myChart = new Chart(context, {
        type: 'doughnut',
        data: {
            labels: chartData.labels,
            datasets: [{
                label: chartData.label,
                data: chartData.data,
                backgroundColor: [
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 99, 132, 0.2)'
                ],
                borderColor: [
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 99, 132, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {}
    });
}

// 가입자/탈퇴자 차트
function drawUserChart(response) {
    var context = document.getElementById('userChart').getContext('2d');
    var myChart = new Chart(context, {
        type: 'bar',
        data: {
            labels: response.months,
            datasets: [
                {
                    label: response.labels[0],
                    data: response.data.map(item => item[0]),
                    backgroundColor: '#00C7E2',
                    maxBarThickness: 30
                },
                {
                    label: response.labels[1],
                    data: response.data.map(item => item[1]),
                    backgroundColor: '#FF6384',
                    maxBarThickness: 30
                }
            ]
        },
        options: {
            responsive: false,
            maintainAspectRatio: false,
            plugins: {
                tooltip: {
                    enabled: true,
                    backgroundColor: '#000',
                    padding: 10
                },
                legend: {
                    display: true,
                    position: 'bottom'
                }
            },
            scales: {
                x: {
                    grid: {
                        display: false,
                    }
                },
                y: {
                    min: 0,
                    max: 50,
                    border: {
                        dash: [5, 5]
                    },
                }
            }
        }
    });
}

// 자녀 연령대 차트
function drawChildAgeChart(childChartData) {
    var context = document.getElementById('AgeChart').getContext('2d');
    var labels = [];
    var data = [];

    for (var i = 0; i < childChartData.labels.length; i++) {
        labels.push(childChartData.labels[i]);
        data.push(childChartData.data[i]);
    }

    var myChart = new Chart(context, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: '자녀평균연령대',
                data: data,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}

// 사용자 연령대 차트
function drawUserAgeChart(userChartData) {
    if (userChartData !== undefined && userChartData !== null) {
        var context = document.getElementById('myChart').getContext('2d');
        var myChart = new Chart(context, {
            type: 'bar',
            data: {
                labels: userChartData.labels,
                datasets: [{
                    label: '사용자 연령대',
                    data: userChartData.data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    } else {
        console.log("userChartData is undefined or null");
    }
}