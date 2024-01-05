const color_prompt = '#0099FA';
const color_command = '#FAF700';
const color_flag = '#FF0000';

const delay_typing = 50;
const delay_underscore = 500;
const delay_word = 1000;
const delay_start = 1000;

consoleText('terminal-body', ['greet', '&#128075; Hello World!', 'introduce --author', '&#129333 Tomasz Strzoda <br> &#128221 T-shaped person, PhD student, AI enthusiast', 'motto', "&#128172 If at first you don't succeed, call it version 1.0"]);

function consoleText(id, words) {
  let target = document.getElementById(id);
  if (!target) {
    console.error("Element with id '" + id + "' not found.");
    return;
  }

  target.innerHTML += '<span style="color:' + color_prompt + '">> </span>';

  let index = 0;

  function displayCharacter(word, charIndex, fontColor, callback) {
    if (charIndex < word.length) {
      color = fontColor;
      if (word[charIndex] === '-' && word[charIndex + 1] === '-') {
        color = color_flag;
      }
      target.innerHTML += '<span style="color:' + color + '">' + word[charIndex] + '</span>';
      charIndex++;
      setTimeout(() => displayCharacter(word, charIndex, color, callback), delay_typing);
    } else {
      setTimeout(() => {
        target.innerHTML += '<span style="color:' + color_command + '">_</span>';
        setTimeout(() => {
          target.innerHTML = target.innerHTML.slice(0, -36); // remove the last character ('_') with <span> tag
          target.innerHTML += '<br>';
          setTimeout(callback, delay_underscore); // delay after removing '_' before starting the next word
        }, delay_underscore); // delay for displaying '_' before removing it
      }, delay_word);
    }
  }

  function displayWord() {
    if (index < words.length) {
      if (index % 2 === 1) {  // every second word without typing animation
        target.innerHTML += words[index] + '<br>' + '<span style="color:' + color_prompt + '">> </span>';
        index++;
        setTimeout(() => {
          displayWord();
        }, delay_word);
      } else {                // word with typing animation
        displayCharacter(words[index], 0, color_command, () => {
          index++;
          displayWord();
        });
      }
    } else {
      // after the end of the animation, appear and disappear "_" for 5 seconds
      displayUnderscoreRepeatedly();
    }
  }

  function displayUnderscoreRepeatedly() {
    let counter = 0;
    const intervalId = setInterval(() => {
      if (counter >= 10) {
        clearInterval(intervalId);
        target.innerHTML = '<span style="color:' + color_prompt + '">> </span>';
        index = 0;
        setTimeout(displayWord, delay_start);
      } else {
        if (counter % 2 === 0) {
          target.innerHTML += '<span style="color:' + color_command + '">_</span>'
        } else {
          target.innerHTML = target.innerHTML.slice(0, -36); // remove the last character ('_') with <span> tag
        }
        counter++;
      }
    }, delay_underscore);
  }

  setTimeout(displayWord, delay_start);
}

