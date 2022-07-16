// Attach a set of endings to a verb root depending on the verb's conjugation.
// Here the endings include either thematic vowels, either short or long depending on the personal ending,
// or 'spacer' vowels for conjugation III (usually i, but also u and e).
// Notice that Conjugation III-i has similarities with both III and IV.
// The conjugation must be specified because there are pairs of verbs that are identical in their
// first-person singular (dictionary) forms but conjugate differently—e.g., volō (fly) and volō (want).
const personalEndingsSet1 = ["ō", "s", "t", "mus", "tis", "nt"];
const personalEndingsSet2 = ["m", "s", "t", "mus", "tis", "nt"];
const conj1PresActIndic = ["ō", "ās", "at", "āmus", "ātis", "ant"];
const conj1PresActSubj = ["em", "ēs", "et", "ēmus", "ētis", "ent"];
const conj1PresPassIndic = ["or", "āris", "ātur", "āmur", "āminī", "antur"];
const conj1PresPassSubj = ["er", "ēris", "ētur", "ēmur", "ēminī", "entur"];
const conj2PresActIndic = ["eō", "ēs", "et", "ēmus", "ētis", "ent"];
const conj2PresActSubj = ["eam", "eās", "eat", "eāmus", "eātis", "eant"]
const conj2PresPassIndic = ["eor", "ēris", "ētur", "ēmur", "ēminī", "entur"];
const conj2PresPassSubj = ["ear", "eāris", "eātur", "eāmur", "eāminī", "eantur"];
const conj3PresActIndic = ["ō", "is", "it", "imus", "itis", "unt"];
const conj3PresActSubj = ["am", "ās", "at", "āmus", "ātis", "ant"];
const conj3PresPassIndic = ["or", "eris", "itur", "imur", "iminī", "untur"];
const conj3PresPassSubj = ["ar", "āris", "ātur", "āmur", "āminī", "antur"];
const conj3IPresActIndic = ["iō", "is", "it", "imus", "itis", "iunt"];
const conj3I4PresActSubj = ["iam", "iās", "iat", "iāmus", "iātis", "iant"];
const conj3IPresPassIndic = ["ior", "ieris", "itur", "imur", "iminī", "iuntur"];
const conj3I4PresPassSubj = ["iar", "iāris", "iātur", "iāmur", "iāminī", "iantur"];
const conj4PresActIndic = ["iō", "īs", "it", "īmus", "ītis", "iunt"];
const conj4PresPassIndic = ["ior", "īris", "ītur", "īmur", "īminī", "iuntur"];
// Imperfect tense indicators and endings
const conj12ImperfActIndic = ["bam", "bās", "bat", "bāmus", "bātis", "bant"];
const conj1ImperfActSubj = ["ārem", "ārēs", "āret", "ārēmus", "ārētis", "ārent"];
const conj1ImperfPassSubj = ["ārer", "ārēris", "ārētur", "ārēmur", "ārēminī", "ārentur"];
const conj2ImperfActSubj = ["ērem", "ērēs", "ēret", "ērēmus", "ērētis", "ērent"];
const conj2ImperfPassSubj = ["ērer", "ērēris", "ērētur", "ērēmur", "ērēminī", "ērentur"];
const conj12ImperfPassIndic = ["bar", "bāris", "bātur", "bāmur", "bāminī", "bantur"];
const conj34ImperfActIndic = ["ēbam", "ēbās", "ēbat", "ēbāmus", "ēbātis", "ēbant"];
const conj3AllImperfActSubj = ["erem", "erēs", "eret", "erēmus", "erētis", "erent"];
const conj3AllImperfPassSubj = ["erer", "erēris", "erētur", "erēmur", "erēminī", "erentur"];
const conj34ImperfPassIndic = ["ēbar", "ēbāris", "ēbātur", "ēbāmur", "ēbāminī", "ēbantur"];
const conj4ImperfActSubj = ["īrem", "īrēs", "īret", "īrēmus", "īrētis", "īrent"];
const conj4ImperfPassSubj = ["īrer", "īrēris", "īrētur", "īrēmur", "īrēminī", "īrentur"];
// Future tense indicators and endings
const conj12FutActIndic = ["bō", "bis", "bit", "bimus", "bitis", "bunt"];
const conj12FutPassIndic = ["bor", "beris", "bitur", "bimur", "biminī", "buntur"];
const conj34FutActIndic = ["am", "ēs", "et", "ēmus", "ētis", "ent"];
const conj34FutPassIndic = ["ar", "ēris", "ētur", "ēmur", "ēminī", "entur"];
// Perfect, pluperfect, and future perfect tense indicators and endings, active voice
const allPerfActIndic = ["ī", "istī", "it", "imus", "istis", "ērunt"];
const allPlupActIndic = ["eram", "erās", "erat", "erāmus", "erātis", "erant"];
const allFPActIndic = ["erō", "eris", "erit", "erimus", "eritis", "erint"];
const allperfSubjIndicator = "eri";
const allPlupActSubj = ["issem", "issēs", "isset", "issēmus", "issētis", "issent"];
// Perfect, pluperfect, and future perfect tense indicators (i.e., forms of "sum" in
// present, imperfect, and future tenses), passive voice
const allPresIndicSum = ["sum", "es", "est", "sumus", "estis", "sunt"];
const allPresSubjSum = ["sim", "sīs", "sit", "sīmus", "sītis", "sint"];
const allImperfIndicSum = ["eram", "erās", "erat", "erāmus", "erātis", "erant"];
const allImperfSubjSum = ["essem", "essēs", "esset", "essēmus", "essētis", "essent"];
const allFutIndicSum = ["erō", "eris", "erit", "erimus", "eritis", "erunt"];

// Attach an active or passive infinitive ending to a verb root depending on the verb's conjugation.
// Deponent verbs will get the passive infinitive endings even when they are active.
const presentActiveInfinitiveEndings = ["āre", "ēre", "ere", "īre"];
const presentPassiveInfinitiveEndings = ["ārī", "ērī", "ī", "īrī"];
const perfectActiveInfinitiveEnding = "isse";
const infinitiveSum = "esse";

// Extract the root of a verb, not to be confused with the present stem.
// The root as used here is the stem minus any thematic vowel particular to a conjugation.
// Irregular verbs do not have predictable roots; their peculiar conjugations will be stored
// as a special field in the database.
function formRoot(verb, conj) {
  var len = verb.length
  var lastLetter = verb.substring(len - 1);
  var lastTwoLetters = verb.substring(len - 2, len);
  var lastThreeLetters = verb.substring(len - 3, len);
  var minusLastLetter = verb.substring(0,len - 1);
  var minusLastTwoLetters = verb.substring(0,len - 2);
  var minusLastThreeLetters = verb.substring(0,len - 3);

  if (conj == "Irregular") {
    return "";
  }
  if (lastThreeLetters == "eor" || lastThreeLetters == "ior") {
    return minusLastThreeLetters;
  } else if (lastTwoLetters == "eō" || lastTwoLetters == "iō" || lastTwoLetters == "or" || lastLetter == "t") {
    return minusLastTwoLetters;
  } else if(lastLetter == "ō") {
    if (conj == "I" || conj == "III") {
      return minusLastLetter;
    }
  }
}

// Depending on the verbs conjugation and the specified voice (active or passive),
// attach the appropriate infinitive ending to the verb root.
function formPresentInfinitive (verb, conj, voice) {
  var infinitiveIndex;
  var myRoot = formRoot(verb,conj);
  var len = verb.length;

  switch (conj) {
    case "Irregular":
      return;
    case "I":
      infinitiveIndex = 0;
      break;
    case "II":
      infinitiveIndex = 1;
      break;
    case "III":
      infinitiveIndex = 2;
      break;
    case "III-i":
      infinitiveIndex = 2;
      break;
    case "IV":
      infinitiveIndex = 3;
  }
  if (verb.substring(len - 2, len) == "or") {
    return myRoot + presentPassiveInfinitiveEndings[infinitiveIndex];
  } else if (voice == "active") {
    return myRoot + presentActiveInfinitiveEndings[infinitiveIndex];
  } else {
    return myRoot + presentPassiveInfinitiveEndings[infinitiveIndex];
  }
}

function formPerfectInfinitive(perfectPart) {
  var len = perfectPart.length;
  var perfectStem, supine, supineStem;
  if (perfectPart.substring(len-1) == "ī") {
    perfectStem = perfectPart.substring(0,len - 1);
    return perfectStem + perfectActiveInfinitiveEnding;
  } else if (perfectPart.substring(len - 3) == "sum") {
    supine = perfectPart.substring(0, len - 6);
    supineStem = supine + "um";
    return supine + " " + infinitiveSum;
  } else {
    return perfectPart + " " + esse;
  }
}

// Attach the conjugational endings from the constants to a verb root depending on the verb's
// conjugation (I through IV) and voice (active or passive).
function conjPresentTenseIndicative(verb, conj, voice) {
  var conjArray = [];
  var conjugation = [];
  var len = verb.length;

  if (verb.substring(len - 1) == "t") {
    return verb;
  }
  if (voice == "active" && verb.substring(len - 1) == "ō") {
    switch (conj) {
      case "Irregular":
        return;
      case "I":
        conjArray = conj1PresActIndic;
        break;
      case "II":
        conjArray = conj2PresActIndic;
        break;
      case "III":
        conjArray = conj3PresActIndic;
        break;
      case "III-i":
        conjArray = conj3IPresActIndic;
        break;
      case "IV":
        conjArray = conj4PresActIndic;
    }
  } else {
    switch (conj) {
      case "Irregular":
        return;
      case "I":
        conjArray = conj1PresPassIndic;
        break;
      case "II":
        conjArray = conj2PresPassIndic;
        break;
      case "III":
        conjArray = conj3PresPassIndic;
        break;
      case "III-i":
        conjArray = conj3IPresPassIndic;
        break;
      case "IV":
        conjArray = conj4PresPassIndic;
    }
  }

  for (i=0; i<=conjArray.length -1; i++) {
    conjugation.push(formRoot(verb,conj) + conjArray[i]);
  }
  return conjugation;
}

function conjPresentTenseSubjunctive(verb, conj, voice) {
  var conjArray = [];
  var conjugation = [];
  var len = verb.length;

  if (verb.substring(len - 1) == "t") {
    if (conj == "II") {
      return conj2PresPassSubj[2];
    }
    if (conj == "III") {
      return conj3PresActSubj[2];
    }
  }

  if (voice == "active" && verb.substring(len - 1) == "ō") {
    switch (conj) {
      case "Irregular":
        return;
      case "I":
        conjArray = conj1PresActSubj;
        break;
      case "II":
        conjArray = conj2PresActSubj;
        break;
      case "III":
        conjArray = conj3PresActSubj;
        break;
      case "III-i":
        conjArray = conj3I4PresActSubj;
        break;
      case "IV":
        conjArray = conj3I4PresActSubj;
    }
  } else {
    switch (conj) {
      case "Irregular":
        return;
      case "I":
        conjArray = conj1PresPassSubj;
        break;
      case "II":
        conjArray = conj2PresPassSubj;
        break;
      case "III":
        conjArray = conj3PresPassSubj;
        break;
      case "III-i":
        conjArray = conj3I4PresPassSubj;
        break;
      case "IV":
        conjArray = conj3I4PresPassSubj;
    }
  }

  for (i=0; i<=conjArray.length -1; i++) {
    conjugation.push(formRoot(verb,conj) + conjArray[i]);
  }
  return conjugation;
}

function conjImperfectTenseIndicative(verb, conj, voice) {
  var conjArray = [];
  var conjugation = [];
  var specialVowel;
  var len = verb.length;

  if (verb.substring(len - 1) == "t") {
    if (conj == "II" || conj == "III") {
      return formRoot(verb,conj) +  conj34ImperfActIndic[2];
    }
  }
  if (voice == "active" && verb.substring(len - 1) == "ō") {
    switch (conj) {
      case "Irregular":
        return;
      case "I":
        specialVowel = "ā";
        conjArray = conj12ImperfActIndic;
        break;
      case "II":
        specialVowel = "ē"
        conjArray = conj12ImperfActIndic;
        break;
      case "III":
        specialVowel = ""
        conjArray = conj34ImperfActIndic;
        break;
      case "III-i":
        specialVowel = "i"
        conjArray = conj34ImperfActIndic;
        break;
      case "IV":
        specialVowel = "i"
        conjArray = conj34ImperfActIndic;
    }
  } else {
    switch (conj) {
      case "Irregular":
        return;
      case "I":
        specialVowel = "ā";
        conjArray = conj12ImperfPassIndic;
        break;
      case "II":
        specialVowel = "ē";
        conjArray = conj12ImperfPassIndic;
        break;
      case "III":
        specialVowel = "";
        conjArray = conj34ImperfPassIndic;
        break;
      case "III-i":
        specialVowel = "i";
        conjArray = conj34ImperfPassIndic;
        break;
      case "IV":
        specialVowel = "i";
        conjArray = conj34ImperfPassIndic;
    }
  }

  for (i = 0; i <= conjArray.length - 1; i++) {
    conjugation.push(formRoot(verb,conj) + specialVowel + conjArray[i]);
  }
  return conjugation;
}

function conjImperfectTenseSubjunctive(verb, conj, voice) {
  var conjArray = [];
  var conjugation = [];
  var len = verb.length;

  if (verb.substring(len - 1) == "t") {
    if (conj == "II") {
      return formRoot(verb,conj) + conj2PresActSubj;
    }
    if (conj == "III") {
      return formRoot(verb,conj) + conj3AllImperfActSubj;
    }
  }

  if (voice == "active" && verb.substring(len - 1) == "ō") {
    switch (conj) {
      case "Irregular":
        return;
      case "I":
        conjArray = conj1ImperfActSubj;
        break;
      case "II":
        conjArray = conj2ImperfActSubj;
        break;
      case "III":
        conjArray = conj3AllImperfActSubj;
        break;
      case "III-i":
        conjArray = conj3AllImperfActSubj;
        break;
      case "IV":
        conjArray = conj4ImperfActSubj;
    }
  } else {
    switch (conj) {
      case "Irregular":
        return;
      case "I":
        conjArray = conj1ImperfPassSubj;
        break;
      case "II":
        conjArray = conj2ImperfPassSubj;
        break;
      case "III":
        conjArray = conj3AllImperfPassSubj;
        break;
      case "III-i":
        conjArray = conj3AllImperfPassSubj;
        break;
      case "IV":
        conjArray = conj4ImperfPassSubj;
    }
  }

  for (i = 0; i <= conjArray.length - 1; i++) {
    conjugation.push(formRoot(verb,conj) + conjArray[i]);
  }
  return conjugation;
}

function conjFutureTense(verb, conj, voice) {
  var conjArray = [];
  var conjugation = [];
  var specialVowel;
  var len = verb.length;

  if (verb.substring(len - 1) == "t") {
    if (conj == "II") {
      return formRoot(verb,conj) + "ē" + conj12FutActIndic[2];
    } else {
      return formRoot(verb,conj) + conj34FutActIndic[2];
    }
  }
  if (voice == "active" && verb.substring(len - 1) == "ō") {
    switch (conj) {
      case "Irregular":
        return;
      case "I":
        specialVowel = "ā";
        conjArray = conj12FutActIndic;
        break;
      case "II":
        specialVowel = "ē"
        conjArray = conj12FutActIndic;
        break;
      case "III":
        specialVowel = ""
        conjArray = conj34FutActIndic;
        break;
      case "III-i":
        specialVowel = "i"
        conjArray = conj34FutActIndic;
        break;
      case "IV":
        specialVowel = "i"
        conjArray = conj34FutActIndic;
    }
  } else {
    switch (conj) {
      case "Irregular":
        return;
      case "I":
        specialVowel = "ā";
        conjArray = conj12FutPassIndic;
        break;
      case "II":
        specialVowel = "ē";
        conjArray = conj12FutPassIndic;
        break;
      case "III":
        specialVowel = "";
        conjArray = conj34FutPassIndic;
        break;
      case "III-i":
        specialVowel = "i";
        conjArray = conj34FutPassIndic;
        break;
      case "IV":
        specialVowel = "i";
        conjArray = conj34FutPassIndic;
    }
  }

  for (i=0; i<=conjArray.length -1; i++) {
    conjugation.push(formRoot(verb,conj) + specialVowel + conjArray[i]);
  }
  return conjugation;
}

function conjPerfectSystemActive(thirdPart, tense, mood) {
  var len = thirdPart.length;
  var perfStem = thirdPart.substring(0,len - 1);
  var tenseIndicator;
  var conjArray = [];
  var conjugation = [];

  if (thirdPart.substring(len - 1) == "ī") {
    if (mood == "indicative") {
      switch (tense) {
        case "perfect":
          conjArray = allPerfActIndic;
          break;
        case "pluperfect":
          conjArray = allPlupActIndic;
          break;
        case "future perfect":
          conjArray = allFPActIndic;
      }
    } else {
      switch (tense) {
        case "perfect":
          tenseIndicator = allperfSubjIndicator;
          for (i = 0; i < personalEndingsSet2.length; i++) {
            conjArray.push(tenseIndicator + personalEndingsSet2[i]);
          }
          break;
        case "pluperfect":
          conjArray = allPlupActSubj;
          break;
        case "future perfect":
          return "Sorry, the subjunctive mood has no future perfect tense."
      }
    }
  } else {
    return "Sorry, that won't work."
  }
  for (i=0; i<conjArray.length; i++) {
    conjugation.push(perfStem + conjArray[i]);
  }
  return conjugation;
}

function conjPerfectSystemPassive(supine,tense,gender,mood) {
  var len = supine.length;
  var supineStem = supine.substring(0,len - 2);
  var singularEnding, pluralEnding;
  var i = 0
  var conjArray = [];
  var conjugation = [];

  switch (gender) {
    case "m":
      singularEnding = "us";
      pluralEnding = "ī";
      break;
    case "f":
      singularEnding = "a";
      pluralEnding = "ae";
      break;
    case "n":
      singularEnding = "um";
      pluralEnding = "a";
  }
  if (mood == "indicative") {
    switch (tense) {
      case "perfect":
        conjArray = allPresIndicSum;
        break;
      case "pluperfect":
        conjArray = allImperfIndicSum;
        break;
      case "future perfect":
        conjArray = allFutIndicSum;
    }
  } else {
    switch (tense) {
      case "perfect":
        conjArray = allPresSubjSum;
        break;
      case "pluperfect":
        conjArray = allImperfSubjSum;
        break;
      case "future perfect":
        return "Sorry, the subjunctive mood doesn't have a future perfect tense."
    }
  }
  do {
    conjugation.push(supineStem + singularEnding + " " + conjArray[i]);
    i++;
  }
  while (i < 3);

  do {
    conjugation.push(supineStem + pluralEnding + " " + conjArray[i]);
    i++;
  }
  while (i < 6);
  return conjugation;
}

function completeConjugation(firstPart,thirdPart,supine,conj,gender) {
  var len = firstPart.length;
  var isNotDeponent = firstPart.substring(len - 1) == "ō";
  console.log("Active Infinitive = " + formPresentInfinitive(firstPart,conj,"active"));
  if (isNotDeponent) {
    console.log("Passive Infinitive = " + formPresentInfinitive(firstPart,conj,"passive"));
  }
  console.log("Present Active:")
  console.log(conjPresentTenseIndicative(firstPart, conj, "active"));
  if (isNotDeponent) {
    console.log("Present Passive:")
    console.log(conjPresentTenseIndicative(firstPart, conj, "passive"));
  }
  console.log("Imperfect Active:");
  console.log(conjImperfectTenseIndicative(firstPart, conj, "active"));
  if (isNotDeponent) {
    console.log("Imperfect Passive:");
    console.log(conjImperfectTenseIndicative(firstPart, conj, "passive"));
  }
  console.log("Future Active:");
  console.log(conjFutureTense(firstPart, conj, "active"));
  if (isNotDeponent) {
    console.log("Future Passive:");
    console.log(conjFutureTense(firstPart, conj, "passive"));
  }
  console.log("Perfect Active:");
  if (thirdPart.substring(thirdPart.length - 1) == "ī") {
    console.log(conjPerfectSystemActive(thirdPart, "perfect"));
  } else {
    console.log(conjPerfectSystemPassive(thirdPart, "perfect", gender));
  }
  if (supine !== null) {
    console.log("Perfect Passive:");
    console.log(conjPerfectSystemPassive(supine, "perfect", gender));
  }
  console.log("Pluperfect Active:");
  if (thirdPart.substring(thirdPart.length - 1) == "ī") {
    console.log(conjPerfectSystemActive(thirdPart, "pluperfect"));
  } else {
    console.log(conjPerfectSystemPassive(thirdPart, "pluperfect", gender));
  }
  if (supine !== null) {
    console.log("Pluperfect Passive:");
    console.log(conjPerfectSystemPassive(supine, "pluperfect", gender));
  }
  console.log("Future Perfect Active:");
  if (thirdPart.substring(thirdPart.length - 1) == "ī") {
    console.log(conjPerfectSystemActive(thirdPart, "future perfect"));
  } else {
    console.log(conjPerfectSystemPassive(thirdPart, "future perfect", gender));
  }
  if (supine !== null) {
    console.log("Future Perfect Passive:");
    console.log(conjPerfectSystemPassive(supine, "future perfect", gender));
  }
}

completeConjugation("pōnō", "pōsuī", "pōsitum", "III", "f")
