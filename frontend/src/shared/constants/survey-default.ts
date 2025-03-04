import { Survey } from "../../libs/generated/typescript-angular";

export function getDefaultSurvey(): Survey {
  return {
    id: '',
    name: 'Loading',
    type: 'EPISODE',
    status: 'PENDING',
    startTime: new Date().toISOString(),
    endTime: new Date().toISOString(),
    questions: [],
    totalPoints: 0,
    pointsEarned: 0,
  };
}
