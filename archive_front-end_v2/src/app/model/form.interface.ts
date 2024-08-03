import { NgForm } from "@angular/forms";

export interface IFormComponent {
    onSubmit(form: NgForm): void;
}