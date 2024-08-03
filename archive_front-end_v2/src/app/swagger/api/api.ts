export * from './authController.service';
import { AuthControllerService } from './authController.service';
export * from './inviteController.service';
import { InviteControllerService } from './inviteController.service';
export * from './leagueController.service';
import { LeagueControllerService } from './leagueController.service';
export * from './surveyController.service';
import { SurveyControllerService } from './surveyController.service';
export const APIS = [AuthControllerService, InviteControllerService, LeagueControllerService, SurveyControllerService];
