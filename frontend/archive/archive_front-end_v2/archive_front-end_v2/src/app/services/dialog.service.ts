import { ComponentType } from '@angular/cdk/portal';
import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { IFormComponent } from '../model/form.interface';

@Injectable({
  providedIn: 'root'
})
export class DialogService {
  constructor(
    private dialog: MatDialog
  ) { }

  public openFormDialog(formComponent: ComponentType<IFormComponent>): MatDialogRef<IFormComponent> {
    return this.dialog.open(formComponent, {
      height: '400px',
      width: '600px',
    });
  }
}
